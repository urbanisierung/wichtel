import * as sendgrid from "@sendgrid/mail";

const wichtels = [
  {
    name: "name",
    email: "name@mail.com"
  },
  {
    name: "secondName",
    email: "secondName@mail.com"
  }
];

export class WichtelMaster {
  private toSend: Array<{ name: string; email: string; wichtel: string }> = [];

  constructor() {
    this.randomizeWichtels();
    console.log(JSON.stringify(this.toSend));
  }

  public async send() {
    sendgrid.setApiKey(process.env.SENDGRID_API_KEY);
    this.toSend.forEach(async m => {
      await this.sendOneMail(m.name, m.email, m.wichtel);
    });
  }

  private async sendOneMail(name: string, email: string, wichtel: string) {
    console.log("sending mail to " + name + " with wichtel " + wichtel);
    const msg = {
      to: email,
      from: "wichtelmaster@nordpol.de",
      templateId: process.env.SENDGRID_TEMPLATE_ID,
      dynamic_template_data: {
        name,
        wichtel
      }
    };
    await sendgrid.send(msg);
  }

  private randomizeWichtels() {
    let receipiencts = wichtels;
    wichtels.forEach(w => {
      let wichtel = receipiencts[this.random(0, receipiencts.length - 1)];
      while (w.name === wichtel.name) {
        wichtel = receipiencts[this.random(0, receipiencts.length - 1)];
      }
      receipiencts = receipiencts.filter(r => r.name !== wichtel.name);

      const item = {
        name: w.name,
        email: w.email,
        wichtel: wichtel.name
      };
      this.toSend.push(item);
    });
  }

  private random(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }
}

const wichtelMaster = new WichtelMaster();
wichtelMaster.send();
