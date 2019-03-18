
const Telegraf = require('telegraf')
const Koa = require('koa')
const koaBody = require('koa-body')

const bot = new Telegraf(process.env.BOT_TOKEN)
bot.command('image', (ctx) => ctx.replyWithPhoto({ url: 'https://picsum.photos/200/300/?random' }))

bot.telegram.setWebhook('https://short-cougar-89.localtunnel.me')

const app = new Koa()
app.use(koaBody())
app.use((ctx, next) => ctx.method === 'POST' || ctx.url === '/secret-path'
  ? bot.handleUpdate(ctx.request.body, ctx.response)
  : next()
)
app.listen(3000)
