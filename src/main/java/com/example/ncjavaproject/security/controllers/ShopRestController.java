package com.example.ncjavaproject.security.controllers;

import com.example.ncjavaproject.entities.Attribute;
import com.example.ncjavaproject.entities.LinkValue;
import com.example.ncjavaproject.entities.ObjectDB;
import com.example.ncjavaproject.entities.Value;
import com.example.ncjavaproject.security.dto.CatalogProductDto;
import com.example.ncjavaproject.security.dto.OrderItemInfoDto;
import com.example.ncjavaproject.security.dto.ProductAmountDto;
import com.example.ncjavaproject.security.entities.Order;
import com.example.ncjavaproject.security.entities.OrderItem;
import com.example.ncjavaproject.security.entities.User;
import com.example.ncjavaproject.security.jwt.JwtTokenProvider;
import com.example.ncjavaproject.security.services.OrderItemService;
import com.example.ncjavaproject.security.services.OrderService;
import com.example.ncjavaproject.security.services.UserService;
import com.example.ncjavaproject.services.*;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shop")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShopRestController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    private final ObjectService objectService;
    private final ObjectTypeService objectTypeService;
    private final AttributeService attributeService;
    private final ValueService valueService;
    private final LinkValueService linkValueService;

    public ShopRestController(JwtTokenProvider jwtTokenProvider,
                              UserService userService,
                              OrderService orderService,
                              OrderItemService orderItemService,
                              ObjectService objectService,
                              AttributeService attributeService,
                              ValueService valueService,
                              ObjectTypeService objectTypeService,
                              LinkValueService linkValueService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.objectService = objectService;
        this.attributeService = attributeService;
        this.valueService = valueService;
        this.objectTypeService = objectTypeService;
        this.linkValueService = linkValueService;
    }

    @GetMapping("profile")
    public ResponseEntity<Map<Object, Object>> profileData(
            @RequestHeader("Authorization") String header
    ) {
        try {
            User user = userService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.getToken(header)));

            Map<Object, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("firstname", user.getFirstName());
            response.put("lastname", user.getLastName());
            response.put("dateOfBirth", user.getDateOfBirth());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);

        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> userOrders(
            @RequestHeader("Authorization") String header
    ) {
        try {
            User user = userService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.getToken(header)));
            return ResponseEntity.ok(orderService.getOrdersByUserId(user.getId()));
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("order_{order_id}/orderItems")
    public ResponseEntity<List<OrderItemInfoDto>> orderItems(
            @RequestHeader("Authorization") String header,
            @PathVariable("order_id") Long orderId
    ) {
        User user;
        Order order;
        try {
            user = userService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.getToken(header)));
            order = orderService.getById(orderId);
            if (order == null || user == null || !user.getId().equals(order.getUserId())) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderItemInfoDto> orderItemInfoList = new ArrayList<>();
        List<OrderItem> orderItems = orderItemService.getAllByOrderId(orderId);

        long priceAttributeId = -1L;
        OrderItemInfoDto orderItemInfoDto;

        for (OrderItem orderItem : orderItems) {
            ObjectDB product = objectService.getObject(orderItem.getProductId());

            if (priceAttributeId == -1L) {
                priceAttributeId = attributeService
                        .getAllAttributesIncludingParents(product.getObjectTypeId())
                        .stream()
                        .filter(attribute -> attribute.getName().equals("Price"))
                        .mapToLong(Attribute::getId)
                        .findFirst().orElse(-2L);
            }
            orderItemInfoDto = new OrderItemInfoDto();
            try {
                orderItemInfoDto.setTotalPrice(
                        Double.parseDouble(
                                valueService.getValueByObjectIdAndAttributeId
                                        (product.getId(), priceAttributeId).getValue()
                        ) * orderItem.getAmount()
                );
            } catch (NumberFormatException e) {
                continue;
            }
            orderItemInfoDto.setOrderItemId(orderItem.getId());
            orderItemInfoDto.setOrderId(orderItem.getOrderId());
            orderItemInfoDto.setProductId(orderItem.getProductId());
            orderItemInfoDto.setProductName(product.getName());
            orderItemInfoDto.setAmount(orderItem.getAmount());

            orderItemInfoList.add(orderItemInfoDto);
        }
        return ResponseEntity.ok(orderItemInfoList);
    }

    @GetMapping("validateToken")
    public ResponseEntity<Boolean> validateToken(
            @RequestHeader("Authorization") String header
    ) {
        return ResponseEntity.ok(
                jwtTokenProvider.validateToken(jwtTokenProvider.getToken(header))
        );
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(
            @RequestHeader("Authorization") String header,
            @PathVariable("orderId") Long orderId
    ) {
        try {
            Order order = orderService.getById(orderId);
            User user = userService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.getToken(header)));
            if (order == null || user == null || !order.getUserId().equals(user.getId())) {
                return ResponseEntity.ok(false);
            }
        } catch (JwtException e) {
            return ResponseEntity.ok(false);
        }
        orderService.deleteById(orderId);
        return ResponseEntity.ok(true);
    }

    @GetMapping("getCatalog")
    public ResponseEntity<List<CatalogProductDto>> getCatalog() {

        List<ObjectDB> objects = objectService.getProducts();
        Map<String, String[]> attributes;
        CatalogProductDto catalogProduct;
        List<CatalogProductDto> catalogProducts = new ArrayList<>();

        for (ObjectDB object : objects) {
            catalogProduct = new CatalogProductDto();
            catalogProduct.setProductTypeName(objectTypeService.getObjectType(object.getObjectTypeId()).getName());

            attributes = attributeService
                    .getAllAttributesIncludingParents(object.getObjectTypeId())
                    .stream()
                    .collect(Collectors.toMap(
                            Attribute::getName,
                            attribute -> {
                                Long attributeTypeId = attribute.getAttributeTypeId();
                                String[] result = new String[]{"", ""};
                                if (attributeTypeId.equals(Attribute.ATTRIBUTE_TYPE_ID_TEXT)) {

                                    Value value = valueService.getValueByObjectIdAndAttributeId(
                                            object.getId(), attribute.getId());
                                    result[0] = value == null ? "" : value.getValue();

                                    value = valueService.getValueByObjectIdAndAttributeId(
                                            object.getId(), attribute.getId());
                                    Date date = value == null ? null : value.getDateValue();
                                    result[1] = date == null ? "" : date.toString();
                                }
                                if (attributeTypeId.equals(Attribute.ATTRIBUTE_TYPE_ID_LINK)) {
                                    LinkValue linkValue = linkValueService.getLinkValueByObjectIdAndAttributeId(
                                            object.getId(), attribute.getId());
                                    result[0] = linkValue == null ? "" :
                                            objectService.getObject(linkValue.getValueObjectId()).getName();
                                }
                                return result;
                            }
                    ));
            try {
                catalogProduct.setPrice(Double.parseDouble(attributes.get("Price")[0]));
                catalogProduct.setAmount(Long.parseLong(attributes.get("Amount")[0]));
            } catch (NumberFormatException e) {
                continue;
            }
            attributes.remove("Price");
            attributes.remove("Amount");
            catalogProduct.setAttributesAndValues(new HashMap<>(attributes));

            catalogProduct.setProductId(object.getId());
            catalogProduct.setProductName(object.getName());
            catalogProducts.add(catalogProduct);
        }

        return ResponseEntity.ok(catalogProducts);
    }

    @PostMapping("addOrder")
    public String addOrder(
            @RequestHeader("Authorization") String header,
            @RequestBody List<ProductAmountDto> orderItemInfo
    ) {
        String falseResult = "false";
        String trueResult = "true";
        String wrongAmountResult = "Количество товаров в корзине неверно в связи с " +
                "обновлёнными данными о количестве товаров на складе. Пожалуйста, " +
                "уточните актуальное количество в разделе \"Каталог\"";

        User user;
        try {
            user = userService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.getToken(header)));
        } catch (JwtException e) {
            return falseResult;
        }

        for (ProductAmountDto pad : orderItemInfo) {
            Long productId = pad.getProductId();
            Long amount = pad.getAmount();
            if (!objectService.existsById(productId)) return falseResult;

            Value value = attributeService
                    .getAllAttributesIncludingParents(
                            objectService.getObject(productId).getObjectTypeId())
                    .stream()
                    .filter(attribute -> "Amount".equals(attribute.getName()))
                    .map(attribute -> valueService.getValueByObjectIdAndAttributeId(productId, attribute.getId()))
                    .findFirst().orElse(null);

            if (value == null) return falseResult;
            try {
                if (Long.parseLong(value.getValue()) < amount) {
                    return wrongAmountResult;
                }
            } catch (NumberFormatException e) {
                return falseResult;
            }
        }

        Long userId = user.getId();
        Order order = orderService.addOrder(userId);
        Long orderId = order.getId();

        orderItemInfo.forEach(productAmountDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productAmountDto.getProductId());
            orderItem.setAmount(productAmountDto.getAmount());
            System.out.println(orderItem);
            System.out.println(
            orderItemService.addOrderItem(orderItem));
        });

        return trueResult;
    }
}








