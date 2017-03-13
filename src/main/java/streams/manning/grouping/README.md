# Stream Grouping examples

## SimpleGroupings

Output of simpleGrouping.  It is a Map of lists, the key of the map is DishType, ie

```
Map<DishType, Dish>

{OTHER=[Dish{name=french fries, vegetarian=true, calories=530, type=OTHER},
        Dish{name=rice, vegetarian=true, calories=350, type=OTHER},
        Dish{name=season fruit, vegetarian=true, calories=120, type=OTHER},
        Dish{name=pizza, vegetarian=true, calories=550, type=OTHER}],
FISH=[Dish{name=prawns, vegetarian=false, calories=400, type=FISH},
        Dish{name=salmon, vegetarian=false, calories=450, type=FISH}],
MEAT=[Dish{name=pork, vegetarian=false, calories=800, type=MEAT},
        Dish{name=beef, vegetarian=false, calories=700, type=MEAT},
        Dish{name=chicken, vegetarian=false, calories=400, type=MEAT}]}
```


Output of the multiLevelGrouping method.  Note it is a Map of Map of lists.  The outer map key is DishType.  The Inner
map key is Diet/Normal/Fat (ie calorcicGrouping)

```
Map<DishType, Map<CaloricGroup, Dish>>

{OTHER={NORMAL=[Dish{name=french fries, vegetarian=true, calories=530, type=OTHER},
                Dish{name=pizza, vegetarian=true, calories=550, type=OTHER}],
        DIET=[Dish{name=rice, vegetarian=true, calories=350, type=OTHER},
              Dish{name=season fruit, vegetarian=true, calories=120, type=OTHER}]},
 FISH={NORMAL=[Dish{name=salmon, vegetarian=false, calories=450, type=FISH}],
        DIET=[Dish{name=prawns, vegetarian=false, calories=400, type=FISH}]},
MEAT={FAT=[Dish{name=pork, vegetarian=false, calories=800, type=MEAT}],
        NORMAL=[Dish{name=beef, vegetarian=false, calories=700, type=MEAT}],
        DIET=[Dish{name=chicken, vegetarian=false, calories=400, type=MEAT}]}}
```
