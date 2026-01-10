def response(hey_bob):
    hey_bob = hey_bob.strip()

    if (hey_bob != ""):
        
        is_question = hey_bob[-1] == "?"
        is_upper = hey_bob.isupper()
        
        if(is_upper and is_question):
            return "Calm down, I know what I'm doing!"
        elif(is_upper):
            return "Whoa, chill out!" 
        elif(is_question):
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
        
