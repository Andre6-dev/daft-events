alter table user_management.tokens
    alter column token_type type varchar(500) using token_type::varchar(500);