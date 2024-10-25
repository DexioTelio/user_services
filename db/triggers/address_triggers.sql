CREATE TRIGGER update_address_update_at
BEFORE UPDATE ON "Address"
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();
