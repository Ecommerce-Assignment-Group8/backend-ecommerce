-- 10 TRAINEES
INSERT INTO "user" (role, phone_number, email, password, date_of_birth, full_name, gender, is_trainee, goal, height, weight, is_trainer, is_businesses)
VALUES
    ( 'TRAINEE', '0901000001', 'trainee1@example.com', 'password123', '1995-05-15', 'Alice Johnson', 'FEMALE', true, 'Weight Loss', '165cm', '60kg', false, false),
    ( 'TRAINEE', '0901000002', 'trainee2@example.com', 'password123', '1992-10-20', 'Bob Smith', 'MALE', true, 'Muscle Gain', '180cm', '85kg', false, false),
    ( 'TRAINEE', '0901000003', 'trainee3@example.com', 'password123', '1998-01-12', 'Charlie Davis', 'MALE', true, 'Endurance', '175cm', '70kg', false, false),
    ( 'TRAINEE', '0901000004', 'trainee4@example.com', 'password123', '1994-03-25', 'Diana Prince', 'FEMALE', true, 'Flexibility', '160cm', '52kg', false, false),
    ( 'TRAINEE', '0901000005', 'trainee5@example.com', 'password123', '1990-07-30', 'Ethan Hunt', 'MALE', true, 'General Fitness', '182cm', '78kg', false, false),
    ( 'TRAINEE', '0901000006', 'trainee6@example.com', 'password123', '1996-12-05', 'Fiona Glenanne', 'FEMALE', true, 'Strength', '168cm', '58kg', false, false),
    ( 'TRAINEE', '0901000007', 'trainee7@example.com', 'password123', '1993-09-14', 'George Miller', 'MALE', true, 'Weight Loss', '170cm', '90kg', false, false),
    ( 'TRAINEE', '0901000008', 'trainee8@example.com', 'password123', '1997-04-22', 'Hannah Abbott', 'FEMALE', true, 'Muscle Gain', '162cm', '55kg', false, false),
    ( 'TRAINEE', '0901000009', 'trainee9@example.com', 'password123', '1991-11-11', 'Ian Wright', 'MALE', true, 'Marathon Training', '178cm', '72kg', false, false),
    ( 'TRAINEE', '0901000010', 'trainee10@example.com', 'password123', '1999-02-28', 'Jenny Kim', 'FEMALE', true, 'Yoga', '158cm', '48kg', false, false);

-- 20 TRAINERS
INSERT INTO "user" ( role, phone_number, email, password, full_name, gender, is_trainee, is_trainer, certificate, bio, specialty, experience_year, is_businesses)
VALUES
    ( 'TRAINER', '0902000001', 'trainer1@fit.com', 'coachpass', 'John Matrix', 'MALE', false, true, 'NASM-CPT', 'Former commando turned trainer.', 'Bodybuilding', 10, false),
    ( 'TRAINER', '0902000002', 'trainer2@fit.com', 'coachpass', 'Sarah Connor', 'FEMALE', false, true, 'ACE Fitness', 'High intensity survival training.', 'HIIT', 8, false),
    ( 'TRAINER', '0902000003', 'trainer3@fit.com', 'coachpass', 'Mike Mentzer', 'MALE', false, true, 'IFBB Pro', 'Heavy duty training specialist.', 'Powerlifting', 15, false),
    ( 'TRAINER', '0902000004', 'trainer4@fit.com', 'coachpass', 'Ellen Ripley', 'FEMALE', false, true, 'CrossFit L3', 'Resilience and stamina expert.', 'CrossFit', 5, false),
    ( 'TRAINER', '0902000005', 'trainer5@fit.com', 'coachpass', 'Bruce Wayne', 'MALE', false, true, 'Global Cert', 'Master of multiple disciplines.', 'Martial Arts', 12, false),
    ( 'TRAINER', '0902000006', 'trainer6@fit.com', 'coachpass', 'Lara Croft', 'FEMALE', false, true, 'Adventure Sport', 'Agility and climbing focus.', 'Mobility', 7, false),
    ( 'TRAINER', '0902000007', 'trainer7@fit.com', 'coachpass', 'Rocky Balboa', 'MALE', false, true, 'Boxing Fed', 'Never give up attitude.', 'Boxing', 20, false),
    ( 'TRAINER', '0902000008', 'trainer8@fit.com', 'coachpass', 'Natasha Romanoff', 'FEMALE', false, true, 'Gymnastics Pro', 'Elite level flexibility.', 'Gymnastics', 9, false),
    ( 'TRAINER', '0902000009', 'trainer9@fit.com', 'coachpass', 'Steve Rogers', 'MALE', false, true, 'Army Physical', 'Classic strength training.', 'Strength & Conditioning', 11, false),
    ( 'TRAINER', '0902000010', 'trainer10@fit.com', 'coachpass', 'Wanda Maximoff', 'FEMALE', false, true, 'Yoga Alliance', 'Mind and body connection.', 'Yoga', 4, false),
    ( 'TRAINER', '0902000011', 'trainer11@fit.com', 'coachpass', 'Tony Stark', 'MALE', false, true, 'TechFit', 'Modern equipment specialist.', 'Circuit Training', 6, false),
    ( 'TRAINER', '0902000012', 'trainer12@fit.com', 'coachpass', 'Carol Danvers', 'FEMALE', false, true, 'AirForce Fit', 'Explosive power training.', 'Plyometrics', 8, false),
    ( 'TRAINER', '0902000013', 'trainer13@fit.com', 'coachpass', 'Arthur Curry', 'MALE', false, true, 'Swim Coach', 'Aquatic fitness specialist.', 'Swimming', 10, false),
    ( 'TRAINER', '0902000014', 'trainer14@fit.com', 'coachpass', 'Diana Prince', 'FEMALE', false, true, 'Olympus Cert', 'Ancient strength techniques.', 'Athletics', 15, false),
    ( 'TRAINER', '0902000015', 'trainer15@fit.com', 'coachpass', 'Barry Allen', 'MALE', false, true, 'Track Pro', 'Speed and agility coach.', 'Sprinting', 3, false),
    ( 'TRAINER', '0902000016', 'trainer16@fit.com', 'coachpass', 'Selina Kyle', 'FEMALE', false, true, 'Cat-Fit', 'Balance and stealth movement.', 'Pilates', 6, false),
    ( 'TRAINER', '0902000017', 'trainer17@fit.com', 'coachpass', 'Logan Howlett', 'MALE', false, true, 'Survivalist', 'Endurance and recovery.', 'Calisthenics', 25, false),
    ( 'TRAINER', '0902000018', 'trainer18@fit.com', 'coachpass', 'Jean Grey', 'FEMALE', false, true, 'Zen Master', 'Focus and breathwork.', 'Meditation', 10, false),
    ( 'TRAINER', '0902000019', 'trainer19@fit.com', 'coachpass', 'Thor Odinson', 'MALE', false, true, 'Asgard Heavy', 'Heavy lifting only.', 'Strongman', 100, false),
    ( 'TRAINER', '0902000020', 'trainer20@fit.com', 'coachpass', 'Peter Parker', 'MALE', false, true, 'NY Fitness', 'Agile and functional movement.', 'Parkour', 2, false);

-- 5 BUSINESSES
INSERT INTO "user" ( role, phone_number, email, password, is_trainee, is_trainer, is_businesses, address, tax_code, business_name)
VALUES
    ( 'BUSINESS', '0903000001', 'contact@goldgym.com', 'busipass', false, false, true, '123 Muscle Ave, NY', 'TAX001', 'Golds Gym'),
    ( 'BUSINESS', '0903000002', 'info@yoga-studio.com', 'busipass', false, false, true, '456 Zen Garden, SF', 'TAX002', 'Serenity Yoga'),
    ( 'BUSINESS', '0903000003', 'support@crossfit-box.com', 'busipass', false, false, true, '789 Industrial Way, CHI', 'TAX003', 'The Iron Box'),
    ( 'BUSINESS', '0903000004', 'hello@fitness-first.com', 'busipass', false, false, true, '101 Corporate Plaza, LDN', 'TAX004', 'Fitness First'),
    ( 'BUSINESS', '0903000005', 'admin@swim-center.com', 'busipass', false, false, true, '202 Aquatic Blvd, SYD', 'TAX005', 'Blue Wave Swim Center');

-- 10 PRODUCTS
INSERT INTO product (name, description, price, image, stock_quantity, category) VALUES
('Whey Protein Isolate', 'Sữa tăng cơ tinh khiết, hấp thụ nhanh', 1500000, 'https://cdn.xaxi.vn/tpcn/img/muscletech-isowhey-100-whey-protein-isolate-vanilla-5-lbs-2-27-kg-135924-631656717617.jpg', 50, 'PROTEIN'),
('C4 Original Pre-Workout', 'Tăng cường năng lượng và sự tập trung trước khi tập', 750000, 'https://product.hstatic.net/200000597515/product/c4-original-pre-workout_7e6b13c8bc884404b462d4a43a952518.jpg', 30, 'PRE_WORKOUT'),
('BCAA 5000 Powder', 'Hỗ trợ phục hồi cơ bắp và chống dị hóa', 600000, 'https://www.sporter.com/media/catalog/product/o/n/on_bcaa_5000_40sv_unflavored_1.jpg', 40, 'AMINO_ACIDS'),
('Creatine Monohydrate', 'Tăng sức mạnh và kích thước cơ bắp', 450000, 'https://bizweb.dktcdn.net/100/011/344/products/nutrabio-micronized-creatine-monohydrate-60-servings-lemon-burst.jpg?v=1760429966957', 100, 'CREATINE'),
('Lipo-6 Black Ultra', 'Hỗ trợ đốt mỡ và giảm cân hiệu quả', 850000, 'https://m.media-amazon.com/images/I/712qJJZF4xL._AC_UF1000,1000_QL80_.jpg', 25, 'WEIGHT_MANAGEMENT'),
('Serious Mass 12lbs', 'Sữa tăng cân cho người gầy lâu năm', 1650000, 'https://www.wheystore.vn/images/products/2023/12/15/large/serious-mass-12lbs_1702628517.jpg', 15, 'MASS_GAINER'),
('Multivitamin Daily', 'Bổ sung đầy đủ vitamin và khoáng chất thiết yếu', 350000, 'https://bizweb.dktcdn.net/100/407/286/products/vien-uong-vitamin-tong-hop-kirkland-signature-daily-multivitamin.jpg?v=1604457556127', 60, 'VITAMINS_MINERALS'),
('ZMA Recovery & Sleep', 'Cải thiện giấc ngủ và phục hồi hormone tự nhiên', 550000, 'https://snac.com/cdn/shop/products/ZMA-5_grande.jpg?v=1629790482', 20, 'RECOVERY_SLEEP'),
('Energy Endurance Gel', 'Bổ sung năng lượng tức thì trong khi tập', 50000, 'https://product.hstatic.net/200001007715/product/sprint---isotonic-energy-gel-_-caffeine--fruit-burst_7270946d580c4426a4d0904b40652594.jpg', 200, 'ENERGY_ENDURANCE'),
('Casein Protein Night', 'Protein hấp thụ chậm giúp nuôi cơ ban đêm', 1400000, 'https://www.nutritionwarehouse.co.nz/cdn/shop/files/0001s_0001_maxs-anabolic-night-rich-chocolate-mousse-900g_600x600.jpg?v=1723087597', 10, 'PROTEIN');