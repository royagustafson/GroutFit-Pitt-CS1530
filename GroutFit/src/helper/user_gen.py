import random


# Generates data for user table
class UserGen:

    def __init__(self, count=200, seed=None):

        # Seed random
        if seed:
            random.seed(seed)

        # Open names files
        f = set(open('sample_data/first_names.txt').read().split('\n'))
        m = set(open('sample_data/middle_names.txt').read().split('\n'))
        l = set(open('sample_data/last_names').read().split('\n'))

        # Generate users
        emails = {}
        profiles = {}
        for i in range(count):

            # Generate user
            user = self.User(
                random.choice(f),
                random.choice(m),
                random.choice(l)
            )

            # Check uniqueness
            if user.profile_id in profiles.keys() or user.email in emails.keys():
                count += 1
                continue

            # Add to data structures
            profiles[user.email] = profiles[user.profile_id] = user

    class User:

        def __init__(self, first, middle, last):

            # ID as random 9 digit integer
            self.profile_id = random.randint(100000000, 999999999)

            # Login using first - middle initial - last @ some random
            self.email = self.generate_email(first, middle, last)
            self.password = 'password'
            self.gender = random.choice(['male', 'female'])

            # Sizes
            self.size_shirt = random.choice(['xs', 's', 'm', 'l', 'xl'])
            self.size_pants = random.choice(['xs', 's', 'm', 'l', 'xl'])
            self.size_shoe = random.randint(5, 12)

        @staticmethod
        def generate_email(first, middle, last):

            if random.randint(0, 1) % 2:
                middle = None
            else:
                middle = middle[0]

            domain = random.choice(['gmail', 'yahoo', 'outlook', 'iCloud', 'aol'])

            return "{}{}{}@{}.com".format(first, middle, last, domain)