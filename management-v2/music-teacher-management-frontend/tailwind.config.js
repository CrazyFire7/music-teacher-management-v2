/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#fdfdfd',
        secondary: '#3a3d42',
        accent: '#5e5648',
        tertiary: '#faf4ee'
      },
    },
  },
  plugins: [],
}

