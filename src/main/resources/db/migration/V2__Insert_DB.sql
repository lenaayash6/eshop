
INSERT INTO fc_category (name,description)
VALUES ('cat1','desc');

INSERT INTO fc_company (name,email,address,about)
VALUES ('company1','c1@gmail.com','homs,syria','it is a test');

INSERT INTO fc_customer (first_name,last_name,mobile,email,city,address)
VALUES ('lena','ayash','0934241555','lenaayash6@gmail.com','homs','homs,syria');

INSERT INTO fc_product (name,price,picture_url,description,category_id,company_id)
VALUES ('product1',3000,'url','desc',1,1);

INSERT INTO fc_order (amount,order_status,customer_id)
VALUES (3000,1,1);

