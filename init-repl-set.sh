(sleep 5 && mongo --eval "rs.initiate({_id: 'replset', members: [{_id: 0, host: 'master:27017'}, {_id: 1, host: 'slave:27017'}]})")&
