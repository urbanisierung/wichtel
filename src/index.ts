import * as sendgrid from "@sendgrid/mail";

const wichtels = [
  {
    name: "",
    email: "",
    uuid: "",
  },
];

export class WichtelMaster {
  private toSend: Array<{ name: string; email: string; wichtel: string }> = [];

  constructor() {
    this.randomizeWichtels();
    console.log(this.toSend.map((w) => `${w.name} -> ${w.wichtel}`).join("\n"));
  }

  public async send() {
    sendgrid.setApiKey(process.env.SENDGRID_API_KEY);
    this.toSend.forEach(async (m) => {
      try {
        await this.sendOneMail(m.name, m.email, m.wichtel);
      } catch (error) {
        console.error(error);
        console.log(JSON.stringify(error));
      }
    });
  }

  private async sendOneMail(name: string, email: string, wichtel: string) {
    console.log("sending mail to " + name + " with wichtel " + wichtel);
    const msg = {
      to: email,
      from: { name: "Wichtelmaster", email: "sender@email" },
      templateId: process.env.SENDGRID_TEMPLATE_ID,
      dynamic_template_data: {
        name,
        wichtel,
      },
    };
    await sendgrid.send(msg);
    console.log("done");
  }

  private randomizeWichtels() {
    let receipiencts = this.shuffleArray();
    let newWichtels = this.shuffleArray();
    newWichtels.forEach((w) => {
      let wichtel = receipiencts[this.random(0, receipiencts.length - 1)];
      while (w.name === wichtel.name) {
        wichtel = receipiencts[this.random(0, receipiencts.length - 1)];
      }
      receipiencts = receipiencts.filter((r) => r.name !== wichtel.name);

      const item = {
        name: w.name,
        email: w.email,
        wichtel: wichtel.name,
      };
      this.toSend.push(item);
    });
  }

  private shuffleArray() {
    const newWichtels = [...wichtels];
    let currentIndex = newWichtels.length;
    let randomIndex;

    // While there remain elements to shuffle.
    while (currentIndex > 0) {
      // Pick a remaining element.
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;

      // And swap it with the current element.
      [newWichtels[currentIndex], newWichtels[randomIndex]] = [
        newWichtels[randomIndex],
        newWichtels[currentIndex],
      ];
    }

    return newWichtels;
  }

  private random(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }
}

async function main() {
  console.log(`Hohoho`);
  const wichtelMaster = new WichtelMaster();
  await wichtelMaster.send();
}

main();
