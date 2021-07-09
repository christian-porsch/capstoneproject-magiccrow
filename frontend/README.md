# MagicCrow

Magic The Gathering is a super exciting trading card game and I love to collect cards physically and in Magic Online. With this web app I want to solve the problem of finding actual magiccards and save them to your digital collection.

You can also see prices in US-Dollar, Euro and Magic Online Tix which are actually fetched from scryfall.com.


## Setup

Frontend:

Just run ```npm install``` and ```npm start```

Backend:

Magiccards a downloaded from scryfall.com as bulkdata and imported with MongoDB to bypass the limitation from scryfall API.
Before import, you need to change **id** to **_id**, otherwise MongoDB will set its own id and the magiccard id can't be used. The name of the database in MongoDB is **magiccrow**. The names of the collections can be found in the repository classes.

You need a user for using the app. Just add one in MongoDB and don't forget to hash the password with BCrypt.

[Bulkdata](https://scryfall.com/docs/api/bulk-data) (please use Default Cards)

