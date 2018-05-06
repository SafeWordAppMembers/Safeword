# a flask application to connect to Twilio 
from __future__ import print_function
from twilio.rest import Client
from flask import Flask
from flask import render_template

app = Flask(__name__, template_folder='/Users/maram')

@app.route("/")
def home():
	return render_template('make_Call_from_web.html')


@app.route('/make_call_function')
def make_call():


	# Your Account Sid and Auth Token from twilio.com/console
	account_sid = 'ACa75136d15a43f9ee6e6c170f3f02cea2'
	auth_token = "6a952ae91a9ccf2c36eb0351a710024d"
	client = Client(account_sid, auth_token)

	call = client.calls.create(
	                        url="http://demo.twilio.com/docs/voice.xml",
	                        from_="+12898064104",
	                        to="+14166257020"
	                    )

	print(call.sid)



if __name__ == "__main__":
    app.run()
