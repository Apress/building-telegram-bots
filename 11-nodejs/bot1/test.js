const Telegraf = require('telegraf')

const telegram = new Telegraf(process.env.BOT_TOKEN)

const { Markup } = Telegraf
const inlineMessageRatingKeyboard = Markup.inlineKeyboard([
    Markup.callbackButton('👍', 'like'),
    Markup.callbackButton('👎', 'dislike')
]).extra()

telegram.on('message', (ctx) => ctx.telegram.sendMessage(
    ctx.from.id,
    'Like?',
    inlineMessageRatingKeyboard)
)

telegram.action('like', (ctx) => ctx.editMessageText('🎉 Awesome! 🎉'))
telegram.action('dislike', (ctx) => ctx.editMessageText('okey'))

telegram.startPolling()