/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('daisyui'),
  ],
  daisyui: {
    themes: ['acid', 'night'],
    darkTheme: 'night',
    base: true,
    utils: true,
    logs: true,
    themeRoot: ':root'
  }
}

