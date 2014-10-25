echo '-------------------------------------------------';
echo '| eBanking database population script            |';
echo '-------------------------------------------------';
echo '';
echo '------ MongoDB Service ------';
echo '';
echo '- Checking for MongoDB installation';

if ! type "mongod" > /dev/null; then
  printf '\nERROR: mongod command not found.\nPlease install MongoDB from http://www.mongodb.org and retry.\n\n';
  exit;
fi

echo;
echo 'Please enter the full location of your mongoDB folder.';
while true; do
	read location
    
    if [[ -z "$location" ]]; then 
    	continue
	else
		if [ ! -d "$location" ]; then
  			echo 'Directory does not exist please enter a valid path. (Dont use ~ shortcut)'
		else 
			echo '- Starting MongoDB';
			mongod --dbpath $location > /dev/null &
			break;	
		fi
	fi	
done
echo '';
echo '-----------------------------';
echo '';
echo '------ Backend Service ------';
echo '';
echo '- Starting Backend';

./gradlew run > /dev/null &

echo '- Waiting for Backend to start...';

while ! curl http_code http://localhost:8080/api/customers/ &>/dev/null; do :; done
echo '';
echo '-----------------------------';
echo '';
echo '------ Creating Database Entries ------';
echo '';
echo '- Creating Customers';
curl -H "Content-Type: application/json" -d '{"firstName":"Jorden","lastName":"Whitefield","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
curl -H "Content-Type: application/json" -d '{"firstName":"Ryan","lastName":"Burke","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
curl -H "Content-Type: application/json" -d '{"firstName":"Ben","lastName":"Fletcher","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
curl -H "Content-Type: application/json" -d '{"firstName":"Philip","lastName":"Broomhead","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
curl -H "Content-Type: application/json" -d '{"firstName":"Michael","lastName":"Brown","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
curl -H "Content-Type: application/json" -d '{"firstName":"James","lastName":"Bond","dateOfBirth":16523,"address":{"houseNumber":"15","street":"Test Road","city":"Testingville","county":"Testers","country":"England","postalCode":"TE5 T1N"},"accounts":[]}' http://localhost:8080/api/customers >/dev/null 2>&1
echo '- Customers created';
echo '';
echo '--------------------------------------';
echo '';
echo '------ Stopping Services ------';
echo '';
echo '- Stopping Backend';
killall java
echo '- Stopping MongoDB';
killall mongod
echo '';
echo '-------------------------------';
echo '';
echo '';
echo 'Process complete. Thank you.';


