if (db.getUser("admin") == null) {
  db.createUser(
    {
      user: "admin",
      pwd: "admin",
      roles: [
        {
          role: "readWrite",
          db: "bankingApiDB"
        }
      ]
    }
  );
}
db.bankingAPIs.drop();
db.bankingAPIs.insertOne(
  {
    bankCode : "ALPHA",
    apiBase : "https://developerhub.alfabank.by:8273/partner/",
    version : "1.0.1",
    currencyEndpoint : "/public/rates"
  }
);