CREATE TABLE short_urls (
    id SERIAL PRIMARY KEY,
    original_url TEXT NOT NULL,
    short_url VARCHAR(8) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    visit_count INT DEFAULT 0,
    user_id INT,
    CONSTRAINT fk_user
      FOREIGN KEY(user_id)
	  REFERENCES users(id)
);