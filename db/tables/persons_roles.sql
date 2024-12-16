CREATE TABLE persons_roles (
    person_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (person_id, role_id),
    FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);
