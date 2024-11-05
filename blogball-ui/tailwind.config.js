/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#669bbc" ,
        secondary: "#fdf0d5",
        body: "#003049",
        FooterNavbar: '#fefafe'
      },
    },
  },
  plugins: [],
}

