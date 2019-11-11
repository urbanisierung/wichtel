# wichtel

```bash
 __      __.__       .__     __         .__   
/  \    /  \__| ____ |  |___/  |_  ____ |  |  
\   \/\/   /  |/ ___\|  |  \   __\/ __ \|  |  
 \        /|  \  \___|   Y  \  | \  ___/|  |__
  \__/\  / |__|\___  >___|  /__|  \___  >____/
       \/          \/     \/          \/      
```

This is a small nodejs tool to *wichtel*. I don't know if *to wichtel* is just a german tradition but I could'nt find a good translation.

I was looking for a tool at GitHub before, found some projects, each of them too complicated implemented and not easy to use. That's why I have implemented my own version.

## What's wichtel

You have *x* people and you want to give each of them one counterpart that is your presentee.
It generates the drawing and sends a mail to each giver with his or her corresponding presentee.

## Preconditions

In the meanwhile there are plenty of services that handle mailings for you. I've chosen [SendGrid](https://sendgrid.com/). You need

* a SendGrid API key (set it as env var `SENDGRID_API_KEY`), and
* a email template you want to send (set the id as env var `SENDGRID_TEMPLATE_ID`). Make sure you have the following variables in your template that becomes replaced by SendGrid:
  * `{name}`
  * `{wichtel}`

## Usage

Enrich participants in `index.ts` with name and email.

```bash
npm install
npm run start
```

Happy wichteling!
