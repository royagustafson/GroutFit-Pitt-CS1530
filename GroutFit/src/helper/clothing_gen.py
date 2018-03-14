import json
import random
import argparse


# Generates data for clothing table
class ClothingGen:

    def __init__(self, count, seed):

        # Seed random
        if seed:
            random.seed(seed)

        # Open names files
        clothing = json.load(open('data_rules/clothing.json'))

        # Generate types + items
        type_count = count
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

    def export_types_csv(self):
        csv = "type_id, name, category, price, description"
        for key, clothing_type in self.types.items():
            csv += clothing_type.as_csv() + '\n'
        return csv[:-2] + '\n'

    def export_types_sql(self):
        sql = "INSERT INTO clothing_type " \
              "(type_id, name, category, price, description) " \
              "VALUES\n"
        for key, clothing_type in self.types.items():
            sql += '\t' + clothing_type.as_insert() + ',\n'
        return sql[:-2] + ';\n'

    def export_items_csv(self):
        csv = "item_id, type_id, color, size, quantity, gender"
        for key, clothing_item in self.items.items():
            csv += clothing_item.as_csv() + '\n'
        return csv[:-2] + '\n'

    def export_items_sql(self):
        sql = "INSERT INTO clothing_item " \
              "(item_id, type_id, color, size, quantity, gender) " \
              "VALUES\n"
        for key, clothing_item in self.items.items():
            sql += '\t'
            clothing_item.as_csv() + '\n'
        return sql[:-2] + ';\n'

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

        def as_csv(self):
            return '{}, "{}", "{}", {}, "{}"'.format(
                self.type_id,
                self.name,
                self.category,
                self.price,
                " "
            )

        def as_insert(self):
            return "(" + self.as_csv() + ")"

    class ClothingItem:

        def __init__(self, type_id, color, size, gender):
            # ID as random 9 digit integer
            self.item_id = random.randint(100000000, 999999999)

            self.type_id = type_id
            self.color = color
            self.size = size

            # Quantity between 3 and 50
            self.quantity = random.randint(3, 50)

            # Gender is random unless type
            self.gender = gender

        def as_csv(self):
            return '{}, {}, "{}", "{}", {}, {}'.format(
                self.item_id,
                self.type_id,
                self.color,
                self.size,
                self.quantity,
                self.gender
            )

        def as_insert(self):
            return "(" + self.as_csv() + ")"


if __name__ == "__main__":

    parser = argparse.ArgumentParser(description="Generate clothing data")
    parser.add_argument("-t", default="clothing_types.sql")
    parser.add_argument("-i", default="clothing_items.sql")
    parser.add_argument("-c", default=150)
    parser.add_argument("-s", default=None)

    args = parser.parse_args()

    types_out = open("sample_data/" + args.t, 'w')
    items_out = open("sample_data/" + args.i, 'w')
    Generator = ClothingGen(args.c, args.s)

    if args.t[-4:] == ".sql":
        types_out.write(Generator.export_types_sql())
    elif args.t[-4:] == ".csv":
        types_out.write(Generator.export_types_csv())
    if args.i[-4:] == ".sql":
        items_out.write(Generator.export_items_sql())
    elif args.i[-4:] == ".csv":
        items_out.write(Generator.export_items_csv())