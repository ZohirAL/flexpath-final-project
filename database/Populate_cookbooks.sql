use flexpath_final;

set @mediterranean_id = (select id from cookbooks
							where name = 'Mediterranean Favorites');
insert into cookbook_recipes (cookbook_id, recipe_id)
select @mediterranean_id, id
from recipes
where title in (
	'Gambas al Ajillo: Spanish Garlic Shrimp',
	'Ratatouille: French Provençal vegetable stew',
	'Spaghetti alle Vongole: Italian clam pasta',
	'Moussaka: traditional Greek eggplant casserole',
	'Chicken Tagine: Moroccan chicken stew'
	);

insert into cookbook_recipes (cookbook_id, recipe_id)
select @seafood_id, id
from recipes
where title in (
	'Gambas al Ajillo: Spanish Garlic Shrimp',
	'Spaghetti alle Vongole: Italian clam pasta'
    );
    
insert into cookbook_recipes (cookbook_id, recipe_id)
select @family_id, id
from recipes
where title in (
	'Albondigas en salsa: Spanish meatballs in a tomato sauce',
    'Coq au Vin: French red wine braised chicken',
    'Spanakopita: Greek Spinach and feta pie',
    'Merguez: Moroccan spiced sausages'
    );