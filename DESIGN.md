
Frameworks to be used:
* Jetpack LiveData
* Jetpack MVVM
* Retrofit for service calls
* DataBinding?
* Confetti as a joke for when they click Submit?

Use Postman to test API
API Details: https://api.target.com/mobile_case_study_deals/v1/swagger-ui/index.html?url=/mobile_case_study_deals/v1/openapi.json

Notes: 
MVVM should handle rotation automatically for us (may need to update the payment screen to use MVVM so that credit card number is not lost on rotation)

TO-DO List:
**** Get the project uploaded to Git!
1. Add MVVM and create View/ViewModel - Deal List
2. Create View/ViewModel - Deal Details
3. Add Retrofit to project and create API service for both list and details
4. Use LiveData and retrieve the product list w/ Progress bar
5. Optional: Theme the progress bar as Target (maybe use animations)
6. Update RCV to have dynamic image, and get from Model (might have to use a network image view to load a URL)
7. Format UI according to the comps
8. Handle taps on specific RecyclerView Items to go to Details
9. Get details loaded from servce, and match up to comps
10: Optional: Carts/Lists? Could use Room to cache the list items

- Get a credit card validator algorithm going for the validator
- Get the credit card transactions "working" (We may need some kind of reactive framework to enable/disable submit based on entry)
--- dynamic validation on field
- Write some Espresso tests! 