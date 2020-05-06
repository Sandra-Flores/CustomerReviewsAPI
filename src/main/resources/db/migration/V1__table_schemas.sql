CREATE TABLE product(
  id INT AUTO_INCREMENT primary key,
  name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  description VARCHAR(100) NOT NULL
);

CREATE TABLE review(
    id INT AUTO_INCREMENT primary key,
    title VARCHAR(255) NOT NULL,
    is_top_review BIT NULL,
    time_created  timestamp default CURRENT_TIMESTAMP NULL,
    review_txt VARCHAR(100) NULL,
    product_id  INT NOT NULL
);

CREATE TABLE comment(
    id INT AUTO_INCREMENT primary key,
    title VARCHAR(255) NOT NULL,
    comment_txt VARCHAR(100) NOT NULL,
    time_created  timestamp default CURRENT_TIMESTAMP NULL,
    review_id  INT NOT NULL
);