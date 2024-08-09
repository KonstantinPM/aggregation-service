db = db.getSiblingDB('userdb');

db.createUser({
    user: 'mongo_user',
    pwd: 'mongo_password',
    roles: [
        {role: 'readWrite', db: 'userdb'}
    ]
});

db.users.insert([
    { userId: 'mongo1UserId1', userLogin: 'zizu', userName: 'Zinedin', userSurname: 'Zidan'},
    { userId: 'mongo1UserId2', userLogin: 'figo', userName: 'Luis', userSurname: 'Figo'},
    { userId: 'mongo1UserId3', userLogin: 'iniesta', userName: 'Andres', userSurname: 'Iniesta'}
]);