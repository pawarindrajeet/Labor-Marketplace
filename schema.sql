CREATE TABLE towns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('Farmer', 'Worker')) NOT NULL,
    gender VARCHAR(10),
    town_id INTEGER REFERENCES towns(id)
);

CREATE TABLE job_posts (
    id SERIAL PRIMARY KEY,
    farmer_id INTEGER REFERENCES users(id),
    town_id INTEGER REFERENCES towns(id),
    work_type VARCHAR(200) NOT NULL,
    description TEXT,
    wage NUMERIC(10,2),
    workers_needed INTEGER NOT NULL,
    workers_responded INTEGER DEFAULT 0,
    is_full BOOLEAN DEFAULT FALSE,
    skills_required TEXT,
    preferred_gender VARCHAR(10),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE responses (
    id SERIAL PRIMARY KEY,
    job_id INTEGER REFERENCES job_posts(id),
    worker_id INTEGER REFERENCES users(id),
    responded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (job_id, worker_id)
);

CREATE TABLE worker_availability (
    id SERIAL PRIMARY KEY,
    worker_id INTEGER REFERENCES users(id),
    skills TEXT,
    availability TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
