package com.example.ncjavaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class NCJavaProject {

    public static void main(String[] args) {
        SpringApplication.run(NCJavaProject.class, args);
    }
}
        /*@Bean
    public CommandLineRunner runner(ObjectRepository objectRepository,
                                    AttributeRepository attributeRepository,
                                    ValueRepository valueRepository) {
        return args -> {

            ObjectDB car = objectRepository.save(new ObjectDB("Car"));
            int carId = car.getId();
            List<Value> carValues = Arrays.asList(
                    valueRepository.save(new Value("Company name", "Lada")),
                    valueRepository.save(new Value("Car model name", "Vesta")),
                    valueRepository.save(new Value("Horsepower", 106)),
                    valueRepository.save(new Value("Liters", 1.6)),
                    valueRepository.save(new Value("Kilograms", 1653)),
                    valueRepository.save(new Value("Kilometers per hour", 186)),
                    valueRepository.save(new Value("Body type name", "Sedan"))
            );
            List<Attribute> carAttributes = Arrays.asList(
                    attributeRepository.save(new Attribute("Car company", carId, carValues.get(0).getId())),
                    attributeRepository.save(new Attribute("Car model", carId, carValues.get(1).getId())),
                    attributeRepository.save(new Attribute("Engine power", carId, carValues.get(2).getId())),
                    attributeRepository.save(new Attribute("Engine volume", carId, carValues.get(3).getId())),
                    attributeRepository.save(new Attribute("Weight", carId, carValues.get(4).getId())),
                    attributeRepository.save(new Attribute("Maximum speed", carId, carValues.get(5).getId())),
                    attributeRepository.save(new Attribute("Body type", carId, carValues.get(6).getId()))
            );

            ObjectDB laptop = objectRepository.save(new ObjectDB("Laptop acer nitro 5"));
            int laptopId = laptop.getId();
            // producer processor graphics card ram hdd storage ssd storage main storage device diagonal
            List<Value> laptopValues = Arrays.asList(
                    valueRepository.save(new Value("Company name", "Acer")),
                    valueRepository.save(new Value("Processor name", "Intel(R) Core(TM) i5-10300H CPU @ 2.50GHz 2.50 GHz")),
                    valueRepository.save(new Value("Graphics card name", "NVIDIA GeForce GTX 1650 Ti")),
                    valueRepository.save(new Value("RAM storage (GB)", 8)),
                    valueRepository.save(new Value("HDD storage (GB)", 0)),
                    valueRepository.save(new Value("SSD storage (GB)", 256)),
                    valueRepository.save(new Value("Main storage device type", "SSD")),
                    valueRepository.save(new Value("Screen diagonal (inches)", 17.3))
            );
            {
                attributeRepository.save(new Attribute("Laptop producer", laptopId, laptopValues.get(0).getId()));
                attributeRepository.save(new Attribute("Processor", laptopId, laptopValues.get(1).getId()));
                attributeRepository.save(new Attribute("Graphics card", laptopId, laptopValues.get(2).getId()));
                attributeRepository.save(new Attribute("RAM", laptopId, laptopValues.get(3).getId()));
                attributeRepository.save(new Attribute("HHD storage", laptopId, laptopValues.get(4).getId()));
                attributeRepository.save(new Attribute("SSD storage", laptopId, laptopValues.get(5).getId()));
                attributeRepository.save(new Attribute("Main storage device", laptopId, laptopValues.get(6).getId()));
                attributeRepository.save(new Attribute("Screen diagonal", laptopId, laptopValues.get(7).getId()));
            }

        };
    }*/

