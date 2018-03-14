import json
import random


# Generates data for clothing table
class ClothingGen:

    def __init__(self, count=150, seed=None):

        # Seed random
        if seed:
            random.seed(seed)

        # Open names files
        clothing = json.load(open('data_rules/clothing.json'))

        # Generate types + items
        type_count = count // 10
        self.types = {}
        self.items = {}
        for type in range(type_count):

            # Create type
            category = random.choice(clothing["types"])
            type = self.ClothingType(
                random.choice(clothing["patterns"][category]),
                category
            )

            # Ensure uniqueness, then add to data structure
            if (type.name, type.category) in self.types.keys():
                type_count += 1
                continue

            self.types[(type.name, category)] = type

            # Create items
            item_count = 10
            for item in range(item_count):

                # Gender logic
                if type.category == "Dress" or type.category == "Skirt":
                    gender = "false"
                else:
                    gender = random.choice(["true", "false", "null"])

                # Create item
                item = self.ClothingItem(
                    type.type_id,
                    random.choice(clothing["colors"]),
                    clothing["sizes"][clothing["sizing"][category]],  # Get sizing type, then random size
                    gender
                )

                # Create item using type
                if ((type.name, type.category), item.color) in self.items.keys():
                    item_count += 1
                    continue

                self.items[((type.name, type.category), item.color)] = item

    class ClothingType:
        def __init__(self, descriptor, category):
            # ID as random 9 digit integer
            self.type_id = random.randint(100000000, 999999999)

            # Name as Descriptor
            self.name = descriptor

            # Category as Type
            self.category = category

            # Price as random 2 precision float from 20-100
            self.price = random.randint(20, 100)
            self.price += random.choice([.00, .99, .95])

    class ClothingItem:

        def __init__(self, type_id, color, size, gender):
            # ID as random 9 digit integer
            self.item_id = random.randint(100000000, 999999999)

            self.type_id = type_id
            self.color = color
            self.size = size

            # Quantity between 3 and 50
            quantity = random.randint(3, 50)

            # Gender is random unless type
            self.gender = gender
