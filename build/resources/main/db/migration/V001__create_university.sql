CREATE TABLE "university_data"
(
id SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
country VARCHAR(255) NOT NULL,
state_province VARCHAR(500),
alpha_two_code VARCHAR(255) NOT NULL,
domains JSONB NOT NULL,
web_pages JSONB NOT NULL
);