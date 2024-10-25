CREATE TRIGGER phone_presence_trigger
AFTER INSERT ON person
FOR EACH ROW
EXECUTE FUNCTION check_phone_before_insert();