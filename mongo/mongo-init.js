print("Started Adding the Users.");
db = db.getSiblingDB("cyber");
db.createUser({
  user: "cyber",
  pwd: "cyber",
  roles: [{ role: "readWrite", db: "cyber" }],
});
print("End Adding the User Roles.");