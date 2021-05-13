
CREATE TABLE location(
   id VARCHAR(255) NOT NULL,
   manager_name VARCHAR(255),
   phone VARCHAR(255),
   address_primary VARCHAR(255),
   address_secondary VARCHAR(255),
   country VARCHAR(255),
   town VARCHAR(255),
   postal_code VARCHAR(255),
   PRIMARY KEY(id)
);

CREATE TABLE listingStatus(
   id INT,
   status_name VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE marketplace(
   id INT,
   marketplace_name VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);


CREATE TABLE listings(
   id VARCHAR(255) NOT NULL,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL,
   inventory_item_location_id VARCHAR(255) NOT NULL,
   listing_price NUMERIC NOT NULL,
   currency VARCHAR(255) NOT NULL,
   quantity INT NOT NULL,
   listing_status INT NOT NULL,
   marketplace INT NOT NULL,
   upload_time DATE,
   owner_email_address VARCHAR(255) NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_location FOREIGN KEY (inventory_item_location_id) REFERENCES location(id),
   CONSTRAINT fk_status FOREIGN KEY (listing_status) REFERENCES listingStatus(id),
   CONSTRAINT fk_marketplace FOREIGN KEY (marketplace) REFERENCES marketplace(id)
);