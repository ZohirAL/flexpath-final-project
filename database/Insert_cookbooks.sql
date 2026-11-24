use flexpath_final;
# cookbook 1 I will make it public

insert into cookbooks (
name,
description,
is_public,
owner
)
values (
'Mediterranean Favorites',
'A Collection of classic Mediterranean recipes inspired by the rich culinary traditions of countries such as Spain, France, Italy, Greece and Morocco.',
TRUE,
'admin'
);
# to solve the hardcodes-id issue last_insert_id() will return the auto generated id from the most recent insert. 
set @mediterranean_id = last_insert_id();

# cookbook 2: public
insert into cookbooks (
name,
description,
is_public,
owner
)
values (
'Fresh seafood from the Mediterranean Sea',
'Explore Mediterranean seafood dishes, combining fresh ingredients, aromatic herbs with traditional techniques',
TRUE,
'admin'
);

set @seafood_id = last_insert_id();

# cookbook 3: private
insert into cookbooks (
name,
description,
is_public,
owner
)
values (
'Family Recipe Collection',
'A collection of delicious meals and favorite recipes designed for family enjoyment.',
FALSE,
'admin'
);

set @family_id = last_insert_id();
