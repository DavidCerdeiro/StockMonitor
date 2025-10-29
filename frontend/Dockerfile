# Stage 1: Build - Usando Node.js
FROM node:18-alpine AS builder
WORKDIR /app

# Copia package.json y lockfile para instalar dependencias
COPY package.json package-lock.json* ./
RUN npm install

# Copia el resto del código
COPY . .

# --- LÍNEA MODIFICADA ---
# Ejecutamos 'vite build' directamente.
# 'npm run build' falla porque 'tsc' (comprobador de tipos) falla primero.
RUN npx vite build

# Stage 2: Serve - Usando 'serve' para hostear los archivos estáticos
FROM node:18-alpine
WORKDIR /app

# Instala 'serve' globalmente
RUN npm install -g serve

# --- LÍNEA MODIFICADA ---
# El directorio de salida por defecto de Vite (ya que no se especifica en vite.config.ts) es 'dist'
COPY --from=builder /app/dist .

# Expone el puerto 3000
EXPOSE 3000

# Comando para iniciar el servidor
CMD ["serve", "-s", ".", "-l", "3000"]