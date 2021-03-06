## MongoDB
> MongoDB DOES NOT REQUIRE COLUMNS TO BE SPECIFIED WHEN CREATING A COLLECTION <br>
MongoDB accepts any type of data in its collections <br>
MongoDB basically uses the same data types as JavaScript, with some differences
>> USES ObjectId, Binary Data, Timestamp, Int, Long, Decimal, JavaScript, etc. <br>
DOES NOT USE Undefined, Symbol
<br>

### MongoDB & MySQL
> Collection is analagous to table <br>
Document is analagous to row <br>
Field is analagous to column <br>
<br>

### To use, open a command-line interface and start the server...
> mongod --auth
### ...then, open another command-line interface and login...
> mongo admin -u <USER NAME> -p <USER PASSWORD>
<br>

### Get started with a command-line interface; after logging in...
1. use <DATABASE NAME>
> Use the specified database; if it doesn't exist, it will be created.
2. show dbs
> Shows list of databases
3. db
> Shows the database currently being used
4. show collections
> Shows list of collections in this database
<br>

### CREATE
1. db.createCollection(<'COLLECTION>')
> Creates the designated collection in the database currently being used <br>
This is not necessary, because adding a document will automatically create the collection!
2. show collections
> Shows list of collections in this database
3. db.<COLLECTION NAME>.save(<DOCUMENT>)
> Create a new document in the designated collection; the document can be organized like a JavaScript object <br>
If successful, the response should be "WriteResult({ "nInserted" : 1 })
<br>

### READ
1. db.<COLLECTION NAME>.find();
> Reads all documents within the designated collection
2. db.<COLLECTION NAME>.find({}, { _id: 0, name: 1, married: 1 })
> Reads only certain fields within the designated collection <br>
Any field with the value of 1 or true is read <br>
Field "_id" is read by default, so give value of 0 so it is not read
3. db.<COLLECTION NAME>.find({ <CONDITIONs> }, { <FIELDs> })
> Reads only the designated fields where the conditions are true <br>
&nbsp;&nbsp;&nbsp;ex) db.<COLLECTION NAME>.find({ name: <NAME> }, { _id: 1 }) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Find the field with the designated name and get the _id (data type is ObjectId) <br>
&nbsp;&nbsp;&nbsp;ex) db.<COLLECTION NAME>.find({ $or: [{ age: { $gt: 10 } }, { married: false }] }, { _id: 0, name: 1, age: 1 }) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Find the field with age greater than 10 OR not married, and get the name and age <br>
4. db.<COLLECTION NAME>.find({}, { _id: 0, name: 1, age: 1 }).sort({ age: -1 })
> Order using the "sort()" method <br>
"sort({ <FIELD> : -1 })" for descending & "sort({ <FIELD> : 1 })" for ascending
5. db.<COLLECTION NAME>.find({}, { _id: 0, name: 1, age: 1 }).sort({ age: -1 }).limit(1)
> Designate the number of documents to read by using the "limit(<NUMBER>)" method
6. db.<COLLECTION NAME>.find({}, { _id: 0, name: 1, age: 1 }).sort({ age: -1 }).limit(1).skip(1)
> Designate how many documents to skip by using the "skip(<NUMBER>)" method
<br>

### UPDATE
1. db.<COLLECTION NAME>.update({ name: 'zwei' }, { $set: { comment: 'Hello, I'm changing this field :p' } })
> 1st argument designates which document to update; 2nd argument provides the data to update <br>
WITH "$set", it is possible to change only the designated fields <br>
WITHOUT "$set", the entire document is changed into the object that is the 2nd argument <br>
If successful, the response should be "WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nInserted" : 1 })

### DELETE
1. db.<COLLECTION NAME>.remove({ name: 'eins' })
> 1st argument designates which document to delete <br>
If successful, the response should be "WriteResult({ "nRemoved" : 1 })


### Get started with MongoDB Compass; after logging in...
1. Click 'CREATE DATABASE', enter 'Database Name' and 'Collection Name', then create
2. Enter a database and click 'CREATE COLLECTION', enter the 'Collection Name', then create
