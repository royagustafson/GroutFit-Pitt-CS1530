import json
import random
import argparse


# Generates data for user table
class UserGen:

    def __init__(self, count, seed):

        # Seed random
        if seed:
            random.seed(seed)

        # Open names files
        names = json.load(open('data_rules/names.json'))
        clothing = json.load(open('data_rules/clothing.json'))

        # Generate users
        self.emails = {}
        self.profiles = {}
        for i in range(count):

            # Generate user
            user = self.User(
                random.choice(names["first"]),
                random.choice(names["middle"]),
                random.choice(names["last"]),
                random.choice(clothing["sizes"]["shirt"]),
                random.choice(clothing["sizes"]["pants"])
            )

            # Check uniqueness
            if user.profile_id in self.profiles.keys() or user.email in self.emails.keys():
                count += 1
                continue

            # Add to data structures
            self.profiles[user.email] = self.emails[user.profile_id] = user

    def export_csv(self):
        csv = "profile_id, email, password, size_shirt, size_pants\n"
        for profile_id, user in self.profiles.items():
            csv += user.as_csv() + '\n'
        return csv[:-2] + '\n'

    def export_sql(self):
        sql = "INSERT INTO profile " \
              "(profile_id, email, password, size_shirt, size_pants) " \
              "VALUES \n"
        for profile_id, user in self.profiles.items():
            sql += '\t' + user.as_insert() + ',\n'
        return sql[:-2] + '\n'

    class User:

        def __init__(self, first, middle, last, shirt, pants):

            # ID as random 9 digit integer
            self.profile_id = random.randint(100000000, 999999999)

            # Login using first - middle initial - last
            # Password is always "password"
            self.email = self.generate_email(first, middle, last)
            self.gender = random.choice(['male', 'female'])

            # Sizes
            self.size_shirt = shirt
            self.size_pants = pants

        @staticmethod
        def generate_email(first, middle, last):

            domain = random.choice(['gmail', 'yahoo', 'outlook', 'iCloud', 'aol'])

            if random.randint(0, 1) % 2 and len(middle) >= 1:
                return "{}{}{}@{}.com".format(first, middle[0], last, domain)
            else:
                return "{}{}@{}.com".format(first, last, domain)

        def as_csv(self):
            return '{}, "{}", "{}", "{}", "{}"'.format(
                self.profile_id,
                self.email,
                "password",
                self.size_shirt,
                self.size_pants,
            )

        def as_insert(self):
            return "(" + self.as_csv() + ")"


if __name__ == "__main__":

    parser = argparse.ArgumentParser(description="Generate profile data")
    parser.add_argument("-f", default="profiles.sql")
    parser.add_argument("-c", default=200)
    parser.add_argument("-s", default=None)

    args = parser.parse_args()

    out_file = open("sample_data/" + args.f, 'w')
    Generator = UserGen(args.c, args.s)

    if args.f[-4:] == '.sql':
        out_file.write(Generator.export_sql())
    elif args.f[-4:] == '.csv':
        out_file.write(Generator.export_csv())
