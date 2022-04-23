# 1-2
curl -X GET "http://192.168.1.102:8080/movies?date=2022-05-20&fromTime=12%3A00&toTime=15%3A30" -H "accept: */*"
printf "\n\n\n"
# 3-4
curl -X GET "http://192.168.1.102:8080/screenings/2" -H "accept: */*"
printf "\n\n\n"
# 5-6 
curl -X POST "http://192.168.1.102:8080/reservations" -H "accept: */*" -H "Content-Type: application/json" -d "{\"firstName\":\"Maciek\",\"lastName\":\"Kowalski\",\"screeningId\":2,\"seats\":[{\"row\":3,\"seat\":3,\"ticketType\":\"adult\"},{\"row\":3,\"seat\":4,\"ticketType\":\"child\"}]}"
printf "\n\n\n"