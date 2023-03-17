Feature: Demoblaze Website Application

Scenario: Login Function
Given User is on Home Page
When User enters login credentials
Then Should display Home Page

Scenario Outline: Add Items to Cart

When Add Items "<Items>" to cart
Then Should be added to the cart

Examples: 
| Items        |
| Nexus 6      |
| Sony vaio i5 |

Scenario: Delete an Item

When Delete an Item
Then Item should be deleted 

Scenario: Place Order

When User Place Order
Then Items should be purchased
