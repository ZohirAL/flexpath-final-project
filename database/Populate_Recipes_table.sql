use flexpath_final;
drop table if exists recipes;
insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
1,
1,
'Gambas al Ajillo: Spanish Garlic Shrimp',
'A classic Spanish seafood dish featuring a delicious juicy jumbo shrimp cooked in garlic infused with olive oil',
'1 pound of raw and peeled shrimp
1/2 cup of extra virgin olive oil
10 cloves of fresh minced garlic
1 teaspoon red pepper flakes
1 teaspoon of sweet Spanish paprika
2 tablespoons of dry sherry or dry white wine
Juice of 1/2 lemon
1/2 cup of fresh chopped parsley
Kosher salt to taste',
'Clean and dry the shrimp with paper towels and season to your taste with salt.
In a large skillet heat the olive oil. Add the minced garlic and the red pepper flakes. Cook briefly until fragrant making sure to not burn the garlic.
Add the shrimp and paprika. Stir approximately 2 to 3 minutes to coat everything in the seasoned olive oil and garlic.
Add the sherry or the dry white wine, the lemon juice and the chopped parsley.
Transfer the shrimp and the garlic along with the oil to a serving dish. Serve immediately with crusty bread for dipping.',
10,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
1,
2,
'Albondigas en salsa: Spanish meatballs in a tomato sauce',
'These Spanish style meatballs are made with a falvorful combination of ground beef and pork, moderately cooked in a homemade tomato sauce seasoned with garlic, onion and smoked paprika.',
'FOR THE MEATBALLS:
1 pound of ground beef
1/2 pound of ground pork,
1/4 cup of milk
1/2 cup of breadcrumbs
1 large egg,
2 cloves of minced garlic
2 tablespoons of fresh chopped parsley
1/2 teaspoon of black pepper
1 teaspoon of salt
2 tablespoons of flour for coating
FOR THE SAUCE:
3 tablespoons of olive oil
1 medium onion chopped finely
2 cloves of minced garlic
1 teaspoon smoked Spanish paprika
14 ounces of chopped tomatoes
1/2 cup of white wine
1 bay leaf
salt to taste
Fresh parsley for garnish',
'Prepare the breadcrumbs mixture by combining the milk and the breadcrumbs in a mixing bowl. let it to rest for about 5 minutes.
Form the meatballs by adding egg, garlic parsley salt and pepper to the grounded meats, mix until everything is combined then form the mixture into small meatballs then lightly coat each one with flour.
In a large skillet over medium heat, brown the meatballs for about 7 minutes until all the sides are nicely browned.
Using the same skillet, add olive oil, chopped tomatoes over low heat for about 6 to 7 minutes, then add minced garlic and paprika and cook briefly until fragrant.
Add the other sauce ingredients by pouring the dry wine, the chopped tomatoes along with the bay leaf, add salt and stir everything together for about 10 minutes until the sauce begins to thicken.
Return the meatballs to the skillet and gently turn them in the sauce until they are fully coated and let them to simmer for approximately 15 to 20 minutes.
Sprinkle with fresh parsley then serve warm with rice, fried potatoes or crusty bread.',
50,
'admin',
FALSE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
2,
3,
'Coq au Vin: French red wine braised chicken',
'A delicious tender chicken slowly cooked in red wine with mushrooms, onions, carrots and herbs to serve generously with mashed potatoes or crusty bread',
'4 chicken thighs and 2 drumsticks
3 ounces of diced bacon
4 ounces of quartered mushrooms
2 medium size carrots sliced
1 medium size onion chopped
2 cloves of minced garlic
2 tablespoons of tomato paste
2 tablespoons of all-purpose flour
2 cups of dry red wine
1 cup of chicken broth
2 tablespoons of olive oil
2 bay leaves
2 sprigs fresh thyme
Salt and black pepper to taste
Fresh parsley for garnish',
'Pat the chicken pieces dry and season with salt and pepper.
In a large Dutch oven over medium heat add olive oil. Brown the chicken parts on all sides then set aside in a different dish.
In the same pot add the diced bacon and cook until lightly crisp. Add the carrots and onion and cook until softened, then add the garlic and the tomato paste.
Sprinkle the flour over the cooking vegetables and stir well. Gradually incorporate the red wine and the chicken broth.
Return the chicken parts to the pot. Add the bay leaves and thyme. Cover and let them simmer for 45 to 60 minutes until the chicken is tender and fully cooked.
In a separate pan, brown the mushrooms, then add them to the stew and let everything simmer for 15 minutes.
Garnish with fresh parsley and serve warm.',
90,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
2,
5,
'Ratatouille: French Provençal vegetable stew',
'This dish is inspired by the sunny region in southern France containing colorful vegetables such as eggplant, zucchini, peppers and tomatoes slowly cooked with olive oil, garlic and aromatic herbs.',
'1 eggplant diced
2 medium zucchinis sliced
1 red bell pepper chopped
1 yellow bell pepper chopped
4 medium tomatoes chopped
1 onion diced
3 cloves of minced garlic
3 tablespoons of olive oil
1 tablespoon of tomato paste
1 teaspoon of dried thyme
1 teaspoon of dried oregano
Fresh basil
Salt and pepper to taste',
'Wash and cut the vegetables into bite-sized pieces.
In a large skillet heat the olive oil. add the eggplant and cook until soft and browned. Remove and set aside.
Add the onion and the bell peppers to the same skillet and cook for about 5 minutes then stir in the tomato paste and the garlic.
Add in the remaining vegetables and season with oregano, salt and pepper.
Return the eggplant to the skillet, cover and cook over low heat for about 25 to 30 minutes.
Sprinkle the chopped basil leaves over the vegetables. Serve warm with toasted bread.',
60,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
3,
1,
'Spaghetti alle Vongole: Italian clam pasta',
'Italian seafood pasta made with fresh clams, spaghetti, garlic olive oil and a splash of white wine.',
'1 pound of spaghetti
2.2 pounds of fresh, cleaned clams
4 cloves of thinly sliced garlic
4 tablespoons of extra virgin olive oil
1/2 cup of dry white wine
1/2 teaspoon red pepper flakes
1 small bunch of fresh chopped parsley
1 lemon
Salt and pepper to taste',
'Rinse the clams thoroughly under cold water.
Cook the pasta according to the package instructions. Save a cup of pasta water before draining.
In a large skillet, heat the olive oil over medium heat. Add the sliced garlic and the red pepper flakes.
Add the clams and the white wine into the skillet, cover and let simmer for about 5 to 7 minutes. Cook until the clams are open, discard any closed clams after cooking.
Add the cooked pasta to the skillet and toss everything all together, add the pasta water if needed to create a light sauce.
Stir in the chopped parsley and season with salt and black pepper. Serve warm with a squeeze of lemon if desired.',
30,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
3,
4,
'Antipasto Misto: Italian appetizer platter',
'A colorful platter bringing together cured meats, assorted cheeses and marinated vegetables, olives and fresh bread.',
'1/3 pound of prosciutto, thinly sliced
1/3 pound of sliced salami
1/3 pound sliced mortadella
1/4 pound of assorted Italian cheeses
1/2 cup of green olives
1/2 cup of black olives
1/2 cup of marinated artichoke hearts
1/2 cup of roasted red peppers
1/2 cup of sun-dried tomatoes
1 small jar of pickled vegetables
1 tablespoon of extra virgin olive oil
Fresh basil leaves
Crusty Italian bread',
'Drain the olives, the roasted peppers, the sun-dried tomatoes and the pickled vegetables, then slice the cheeses into manageable pieces.
 Fold or roll the prosciutto, salami, and mortadella and arrange them across a large serving board.
 Place the assorted cheeses in different sections of the board, leaving room for the remaining vegetables.
 Add the olives, the sun-dried tomatoes, the artichoke hearts, the roasted peppers and the pickled vegetables in small portions around the meats and cheeses.
 Drizzle some olive oil over the vegetables and add fresh basil leaves for decoration.
 Add crusty bread before serving.',
 20,
 'admin',
 FALSE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
4,
5,
'Spanakopita: Greek Spinach and feta pie',
'A traditional Greek pastry filled with spinach, crumbled feta cheese, fresh herbs wrapped in a phyllo dough.',
'1.2 pounds of fresh spinach
1/3 pound of crumbled feta cheese
1 small chopped onion
3 green onions sliced
2 large eggs
2 tablespoons fresh parsley, chopped
1 tablespoon fresh dill, chopped
1/4 teaspoon ground nutmeg
1/2 pound phyllo pastry sheets
1/2 cup melted butter or olive oil
Salt and black pepper to taste',
'Wash, cook, squeeze then chop the spinach.
Heat the olive oil in a skillet and add the onion and green onions, cook until softened, combine them with the spinach, crumbled feta, eggs, parsley, dill, nutmeg and the black pepper.
For the pastry: Brush the baking pan with butter or olive oil, lay several sheets of phyllo pastry into the pan while brushing each layer with melted butter.
Spread the spinach mixture evenly over the pastry.
Cover the mixture by adding sheets of phyllo pastry over the pan while brushing each layer with melted butter.
Bake at 375 °F for about 35 to 40 minutes until the pastry is crisp and golden.
Allow the pie to cool before cutting it into portions, serve warm or at room temperature.',
60,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
4,
2,
'Moussaka: traditional Greek eggplant casserole',
'Greek baked dish layered with tender eggplant, seasoned ground beef and a creamy bechamel topping.',
'2 large eggplants, sliced
3/4 pound of ground beef
1 medium onion, finely chopped
3 cloves of minced garlic
1 pound of crushed tomatoes
2 tablespoons tomato paste
1/2 cup dry red wine
2 tablespoons olive oil
1 teaspoon ground cinnamon
1 teaspoon dried oregano
Salt and black pepper to taste

For the béchamel:
4 tablespoons butter
4 tablespoons all-purpose flour
3 cups milk
1/4 teaspoon ground nutmeg
1 large egg
1/2 cup grated Parmesan cheese',
'Slice the eggplants into rounds and arrange them on a baking tray. Brush lightly with olive oil, season with salt, and roast at 400 degrees f for about 20 minutes until tender and golden.
Heat olive oil in a large skillet. Add the chopped onion and cook until softened. Stir in the garlic and ground beef.
Add the tomato paste, crushed tomatoes, red wine, cinnamon, oregano, salt, and black pepper. Simmer for  20 minutes until the sauce thickens.
Make the béchamel: Melt the butter in a pan over medium heat. Stir in the flour and cook briefly. whisk in the milk until smooth. Continue stirring until the sauce thickens. Remove from heat, allow it to cool down, then mix in the egg, nutmeg, and Parmesan cheese.
Arrange half of the roasted eggplant in a baking dish. Spread the meat sauce evenly over it, then add the remaining eggplant. Pour the béchamel over the top and spread evenly.
Place the dish in a preheated oven at 375°F for about 35 to 40 minutes until the topping is golden.
Let the moussaka cool for about 15 to 20 minutes before slicing. Serve warm.',
90,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
5,
3,
'Chicken Tagine: Moroccan chicken stew',
'A fragrant Moroccan chicken dish slowly simmered with preserved lemons, green olives, onions, and traditional spices.',
'2 pounds of chicken thighs or drumsticks
2 medium onions, finely sliced
3 cloves of minced garlic
1 preserved lemon, pulp removed and rind sliced
1 cup green olives
3 tablespoons olive oil
1 teaspoon ground ginger
1 teaspoon ground cumin
1 teaspoon ground turmeric
1/2 teaspoon ground cinnamon
1/2 teaspoon paprika
1/2 teaspoon black pepper
1 cup chicken broth
1 small bunch fresh cilantro, chopped
1 small bunch fresh parsley, chopped
Salt to taste',
'Pat the chicken pieces dry. Combine the seasonings ginger, cumin, turmeric, cinnamon, paprika, and black pepper. Rub the spice mixture over the chicken.
Heat olive oil in a tagine or heavy-bottomed pot over medium heat. Add the sliced onions and cook until soft and golden. Add in the garlic.
Add the seasoned chicken parts and turn them regularly until browned on the outside.
Pour in the chicken broth and add the chopped parsley and half of the cilantro.
Cover the pot and reduce the heat to low. Cook for about 40 to 45 minutes, until tender and fully cooked.
Stir in the preserved lemon rind and green olives. Continue cooking uncovered for another 10 minutes so the sauce can thicken.
Sprinkle the remaining cilantro over the chicken. Serve hot with couscous or warm bread.',
75,
'admin',
TRUE
);

insert into recipes (
country_id,
category_id,
title,
description,
ingredients,
instructions,
prep_time,
owner,
is_public
)
values (
5,
4,
'Merguez: Moroccan spiced sausages',
'A popular North African sausage. Made with ground lamb or beef, garlic, cumin, coriander, and paprika.',
'2 pounds of ground lamb or beef
2 cloves garlic, finely minced
1 teaspoon ground cumin
1 teaspoon ground coriander
1 teaspoon sweet paprika
1/2 teaspoon hot paprika or cayenne pepper
1/2 teaspoon ground fennel
1/2 teaspoon ground caraway
1/2 teaspoon salt
1/4 teaspoon black pepper
1 tablespoon olive oil
2 tablespoons cold water
Natural sausage casings, optional
Harissa, for serving',
'In a small bowl, combine the seasonings such as the cumin, the coriander, the paprika mild and hot, the fennel, the caraway, the salt, and black pepper.
Place the ground lamb or beef in a large bowl. Add the minced garlic, spice mixture, olive oil, and cold water. Mix until combined.
Divide the mixture into portions and shape into small sausages.
Refrigerate the shaped sausages for about 20 to 30 minutes to help them hold their shape during cooking.
Heat a skillet or grill over medium heat. Cook the sausages, turning regularly, until browned on all sides and cooked through.
Allow the sausages to rest briefly before serving. serve with couscous, flatbread, fresh vegetables.',
35,
'admin',
TRUE
);



