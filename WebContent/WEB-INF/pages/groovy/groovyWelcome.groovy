yieldUnescaped '<!DOCTYPE html>'
html {
	head {
		title("Groovy Boot")
	}
	body {
		h2("Welcome to boot camp!")
		form (action: 'Dashboard', method: 'POST') {
			input(type: 'text', name: 'login')
			input(type: 'submit', value: 'Go To Dashboard')
		}
	}
}