import groovy.swing.factory.TDFactory;

yieldUnescaped '<!DOCTYPE html>'
html {
	head {
		title("Dashboard")
		link(rel: "stylesheet", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
	}
	body {
		div(class: "container") {
			h2("Welcome ${name}!")
			form (action: 'addUser') {
				label("Name:")
				input(type: 'text', name: 'newUserName')
				br {}
				label("Email: ")
				input(type: 'text', name: 'newUserEmail')
				br {}
				input(type: 'submit', value: 'Add User')
				br {}
				a(href: "deleteAllUsers", "Delete All Users")
			}
			br{}
			
			br{}
			table {
				tr {
					th("Id")
					th("name")
					th("email")
					th("RestController test")
				}
				if (users) {
					users.each { user ->
						tr {
							td(user.id)
							td(user.name)
							td(user.email)
							td {
								a(href: "deleteUser?userId=${user.id}", "Delete")
							}
							td {
								a(href: "ajax/${user.id}/getUser", "Get User JSON")
							}
						}
					}
				}
			}
			
			form(action: "recreg/passthru") {
				label("Magazine: ")
				input(name: "mag", type: "text")
				label("Account: ")
				input(name: "acct", type: "text")
				input(type:"submit")
			}
		}
	}
}