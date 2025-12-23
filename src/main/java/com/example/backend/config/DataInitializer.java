package com.example.backend.config;

import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Kiểm tra xem đã có dữ liệu chưa
            if (userRepository.count() > 0) {
                System.out.println("Database đã có dữ liệu, bỏ qua việc seed data.");
                return;
            }

            System.out.println("Bắt đầu seed dữ liệu mẫu...");

            // Tạo Users
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Admin User");
            admin.setPhoneNumber("0123456789");
            admin.setRole("ADMIN");
            admin.setTrainee(false);
            admin.setTrainer(false);
            admin.setBusinesses(false);
            admin = userRepository.save(admin);

            User trainer1 = new User();
            trainer1.setEmail("trainer1@example.com");
            trainer1.setPassword(passwordEncoder.encode("trainer123"));
            trainer1.setFullName("Nguyễn Văn A");
            trainer1.setPhoneNumber("0987654321");
            trainer1.setRole("TRAINER");
            trainer1.setTrainer(true);
            trainer1.setTrainee(false);
            trainer1.setBusinesses(false);
            trainer1.setGender(User.Gender.MALE);
            trainer1.setBio("Huấn luyện viên chuyên nghiệp với 5 năm kinh nghiệm");
            trainer1.setSpecialty("Yoga, Pilates");
            trainer1.setExperienceYear(5);
            trainer1.setCertificate("ACE Certified Personal Trainer");
            trainer1 = userRepository.save(trainer1);

            User trainee1 = new User();
            trainee1.setEmail("trainee1@example.com");
            trainee1.setPassword(passwordEncoder.encode("trainee123"));
            trainee1.setFullName("Trần Thị B");
            trainee1.setPhoneNumber("0912345678");
            trainee1.setRole("TRAINEE");
            trainee1.setTrainee(true);
            trainee1.setTrainer(false);
            trainee1.setBusinesses(false);
            trainee1.setGender(User.Gender.FEMALE);
            trainee1.setDateOfBirth("1995-05-15");
            trainee1.setHeight("165");
            trainee1.setWeight("55");
            trainee1.setGoal("Giảm cân và tăng cường sức khỏe");
            trainee1 = userRepository.save(trainee1);

            // Tạo Categories
            Category category1 = new Category();
            category1.setName("Thực phẩm bổ sung");
            category1 = categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Dụng cụ tập luyện");
            category2 = categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Quần áo thể thao");
            category3 = categoryRepository.save(category3);

            Category category4 = new Category();
            category4.setName("Thiết bị theo dõi sức khỏe");
            category4 = categoryRepository.save(category4);

            // Tạo Products
            List<Product> products = new ArrayList<>();

            Product product1 = new Product();
            product1.setName("Whey Protein 2kg");
            product1.setDescription("Bột protein chất lượng cao, giúp phục hồi và phát triển cơ bắp");
            product1.setPrice(1200000);
            product1.setImage("https://example.com/images/whey-protein.jpg");
            product1.setStockQuantity(50);
            product1.setCategories(List.of(category1));
            products.add(product1);

            Product product2 = new Product();
            product2.setName("Tạ tay 5kg");
            product2.setDescription("Tạ tay cao cấp, phù hợp cho tập luyện tại nhà");
            product2.setPrice(350000);
            product2.setImage("https://example.com/images/dumbbell.jpg");
            product2.setStockQuantity(30);
            product2.setCategories(List.of(category2));
            products.add(product2);

            Product product3 = new Product();
            product3.setName("Áo thun thể thao");
            product3.setDescription("Áo thun co giãn, thấm hút mồ hôi tốt");
            product3.setPrice(250000);
            product3.setImage("https://example.com/images/t-shirt.jpg");
            product3.setStockQuantity(100);
            product3.setCategories(List.of(category3));
            products.add(product3);

            Product product4 = new Product();
            product4.setName("Smart Watch Fitness");
            product4.setDescription("Đồng hồ thông minh theo dõi sức khỏe và thể dục");
            product4.setPrice(3500000);
            product4.setImage("https://example.com/images/smartwatch.jpg");
            product4.setStockQuantity(20);
            product4.setCategories(List.of(category4));
            products.add(product4);

            Product product5 = new Product();
            product5.setName("BCAA 500g");
            product5.setDescription("Bổ sung BCAA giúp phục hồi cơ bắp nhanh chóng");
            product5.setPrice(800000);
            product5.setImage("https://example.com/images/bcaa.jpg");
            product5.setStockQuantity(40);
            product5.setCategories(List.of(category1));
            products.add(product5);

            Product product6 = new Product();
            product6.setName("Thảm tập Yoga");
            product6.setDescription("Thảm tập yoga chống trượt, dày 10mm");
            product6.setPrice(200000);
            product6.setImage("https://example.com/images/yoga-mat.jpg");
            product6.setStockQuantity(60);
            product6.setCategories(List.of(category2));
            products.add(product6);

            productRepository.saveAll(products);

            System.out.println("✅ Đã seed dữ liệu mẫu thành công!");
            System.out.println("   - Users: " + userRepository.count());
            System.out.println("   - Categories: " + categoryRepository.count());
            System.out.println("   - Products: " + productRepository.count());
        };
    }
}

