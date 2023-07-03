set -e

mongo <<EOF
db = db.getSiblingDB('cyber')

db.createUser({
  user: 'mongo',
  pwd: 'mongo',
  roles: [{ role: 'readWrite', db: 'cyber' }],
});

EOF