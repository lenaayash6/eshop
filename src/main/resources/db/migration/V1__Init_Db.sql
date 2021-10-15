CREATE SEQUENCE fc_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE fc_category
(
    id      int8 NOT NULL DEFAULT nextval('fc_category_id_seq'),
    name    VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE fc_company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE fc_company
(
    id      int8 NOT NULL DEFAULT nextval('fc_company_id_seq'),
    name    VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),

    about VARCHAR(255) ,
    PRIMARY KEY (id)
);

CREATE SEQUENCE fc_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE fc_customer
(
    id      int8 NOT NULL DEFAULT nextval('fc_customer_id_seq'),
    first_name   VARCHAR(255),
    last_name VARCHAR(255),
    mobile VARCHAR(255),
    email VARCHAR(255),
    city VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE fc_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE fc_order
(
    id      int8 NOT NULL DEFAULT nextval('fc_customer_id_seq'),
    amount    DOUBLE PRECISION,
    order_status int ,
    customer_id  int8 NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE fc_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE fc_product
(
    id      int8 NOT NULL DEFAULT nextval('fc_product_id_seq'),
    name    VARCHAR(255),
    price    DOUBLE PRECISION,
    picture_url  VARCHAR(255),
    description VARCHAR(255),
    category_id  int8 NOT NULL,
    company_id int8 NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE if EXISTS fc_order
    ADD CONSTRAINT fc_customer_fk
        FOREIGN KEY (customer_id) REFERENCES fc_customer;

ALTER TABLE if EXISTS fc_product
    ADD CONSTRAINT fc_category_fk
        FOREIGN KEY (category_id) REFERENCES fc_category;

ALTER TABLE if EXISTS fc_product
    ADD CONSTRAINT fc_company_fk
        FOREIGN KEY (company_id) REFERENCES fc_company;
