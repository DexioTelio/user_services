CREATE TRIGGER phone_presence
AFTER INSERT ON person
FOR EACH ROW
EXECUTE FUNCTION check_phone_before_insert();