ALTER TABLE reports ADD COLUMN external_id uuid;
CREATE INDEX external_id_and_requester ON reports (external_id, requester);
ALTER TABLE reports ADD UNIQUE (external_id, requester);