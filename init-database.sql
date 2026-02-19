-- Labor Marketplace Database Schema
-- PostgreSQL Database Setup Script
-- Run this script with: psql -U postgres -d labor_marketplace -f init-database.sql

-- ============================================
-- Create Tables
-- ============================================

-- Towns table
CREATE TABLE IF NOT EXISTS town (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Users table (Farmers and Workers)
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('Farmer', 'Worker')) NOT NULL,
    gender VARCHAR(10),
    town_id INTEGER REFERENCES town(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Job Posts table
CREATE TABLE IF NOT EXISTS job_post (
    id SERIAL PRIMARY KEY,
    farmer_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    town_id INTEGER REFERENCES town(id),
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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Responses table (Worker interest in jobs)
CREATE TABLE IF NOT EXISTS response (
    id SERIAL PRIMARY KEY,
    job_id INTEGER REFERENCES job_post(id) ON DELETE CASCADE,
    worker_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    responded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (job_id, worker_id)
);

-- Worker Availability table
CREATE TABLE IF NOT EXISTS worker_availability (
    id SERIAL PRIMARY KEY,
    worker_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    skills TEXT,
    availability TEXT,
    wage NUMERIC(10,2),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Create Indexes for Performance
-- ============================================

CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_town ON users(town_id);
CREATE INDEX IF NOT EXISTS idx_users_mobile ON users(mobile);
CREATE INDEX IF NOT EXISTS idx_job_post_farmer ON job_post(farmer_id);
CREATE INDEX IF NOT EXISTS idx_job_post_town ON job_post(town_id);
CREATE INDEX IF NOT EXISTS idx_job_post_is_full ON job_post(is_full);
CREATE INDEX IF NOT EXISTS idx_job_post_created ON job_post(created_at DESC);
CREATE INDEX IF NOT EXISTS idx_response_job ON response(job_id);
CREATE INDEX IF NOT EXISTS idx_response_worker ON response(worker_id);
CREATE INDEX IF NOT EXISTS idx_worker_availability_worker ON worker_availability(worker_id);
CREATE INDEX IF NOT EXISTS idx_worker_availability_created ON worker_availability(created_at DESC);

-- ============================================
-- Populate Towns (Marathi Towns)
-- ============================================

INSERT INTO town (name) VALUES
('Agar'),
('Ankali'),
('Arag'),
('Balwad'),
('Bamnoli'),
('Bedag'),
('Belanki'),
('Bhose'),
('Bijageri'),
('Brahmanpuri'),
('Budhavgaon'),
('Chinchani'),
('Dhavali'),
('Dudhani'),
('Ganeshpur'),
('Garlawad'),
('Gudhe'),
('Gulvanchi'),
('Harnal'),
('Ingalgi'),
('Jambli'),
('Kadiyanal'),
('Kavthepiran'),
('Khanderajuri'),
('Kukudwad'),
('Kupwad'),
('Laxmiwadi'),
('Madihal'),
('Malgaon'),
('Maliwadi'),
('Mangrul'),
('Miraj'),
('Mhaisal'),
('Narwad'),
('Nandre'),
('Nandani'),
('Nimshirgaon'),
('Padali'),
('Patgaon'),
('Rupbhavani'),
('Sagara'),
('Sangli'),
('Sangliwadi'),
('Soni'),
('Tupari'),
('Wanlesswadi'),
('Yesugade'),
('Alate'),
('Amaljheri'),
('Anjani'),
('Balawadi'),
('Bhilawadi'),
('Borgaon'),
('Choudeshwar'),
('Dahiwadi'),
('Dhamani'),
('Dhawali'),
('Dhavalipur'),
('Dongarsoni'),
('Ghanand'),
('Ghatnandre')
ON CONFLICT (name) DO NOTHING;

-- ============================================
-- Sample Data (Optional - for testing)
-- ============================================

-- Sample Farmer User
-- INSERT INTO users (name, mobile, password, role, gender, town_id) VALUES
-- ('राज किसान', '9876543210', 'hashed_password_here', 'Farmer', 'Male', 1);

-- Sample Worker User
-- INSERT INTO users (name, mobile, password, role, gender, town_id) VALUES
-- ('अर्जुन कामगार', '9876543211', 'hashed_password_here', 'Worker', 'Male', 1);

-- Sample Job Post
-- INSERT INTO job_post (farmer_id, town_id, work_type, description, wage, workers_needed, skills_required, preferred_gender) VALUES
-- (1, 1, 'धान की बुवाई', 'धान की बुवाई के लिए कामगार चाहिए', 500.00, 5, 'बुवाई का अनुभव', 'Any');

-- ============================================
-- Views for Common Queries
-- ============================================

-- Active Jobs View
CREATE OR REPLACE VIEW active_jobs AS
SELECT 
    jp.id,
    jp.work_type,
    jp.wage,
    jp.workers_needed,
    jp.workers_responded,
    jp.workers_needed - jp.workers_responded as workers_available,
    u.name as farmer_name,
    u.mobile as farmer_mobile,
    t.name as town_name,
    jp.created_at,
    jp.is_full
FROM job_post jp
JOIN users u ON jp.farmer_id = u.id
JOIN town t ON jp.town_id = t.id
WHERE jp.is_full = FALSE
ORDER BY jp.created_at DESC;

-- Available Workers View
CREATE OR REPLACE VIEW available_workers AS
SELECT 
    wa.id,
    wa.skills,
    wa.wage,
    u.name as worker_name,
    u.mobile as worker_mobile,
    u.gender,
    t.name as town_name,
    wa.created_at
FROM worker_availability wa
JOIN users u ON wa.worker_id = u.id
JOIN town t ON u.town_id = t.id
ORDER BY wa.created_at DESC;

-- ============================================
-- Database Initialization Complete
-- ============================================

-- Display summary
\echo '========================================';
\echo 'Labor Marketplace Database Created';
\echo '========================================';
\echo '';
\echo 'Tables Created:';
\echo '  ✓ town - 60+ Marathi towns';
\echo '  ✓ users - Farmer & Worker profiles';
\echo '  ✓ job_post - Job listings';
\echo '  ✓ response - Worker applications';
\echo '  ✓ worker_availability - Worker profiles';
\echo '';
\echo 'Views Created:';
\echo '  ✓ active_jobs - Active job listings';
\echo '  ✓ available_workers - Available workers';
\echo '';
\echo 'Indexes Created for Performance Optimization';
\echo '========================================';
