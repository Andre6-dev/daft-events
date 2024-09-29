alter table user_management.tokens
    alter column jwt type varchar(500) using token_type::varchar(500);