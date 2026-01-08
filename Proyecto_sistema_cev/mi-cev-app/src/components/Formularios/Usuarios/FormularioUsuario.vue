<template>
  <div class="usuario-wrapper">
    <div class="form-group">
      <label for="rut">Rut</label>
      <input id="rut" :value="form.rutUsuario" @input="emit('update:form', { ...form, rutUsuario: $event.target.value })" type="text" placeholder="Ej: 12.345.678-9"/>
    </div>

    <div class="form-group">
      <label for="email">Correo</label>
      <input id="email" :value="form.email" @input="emit('update:form', { ...form, email: $event.target.value })" type="email" placeholder="usuario@correo.com"/>
    </div>

    <div class="form-group">
      <label for="telefono">Teléfono</label>
      <input id="telefono" :value="form.telefono" @input="emit('update:form', { ...form, telefono: $event.target.value })" type="tel" placeholder="Ej: +56912345678"/>
    </div>

    <div class="form-group">
      <label for="password">Contraseña</label>
      <input id="password" :value="form.password" @input="emit('update:form', { ...form, password: $event.target.value })" type="password" placeholder="********"/>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  form: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['update:form'])
</script>
<style scoped>

.form-usuario {
  background: white;
  
  padding: 30px 25px;
  max-width: 300px;
  width: 90%;
  
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.registro-wrapper {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #d9edc2;
  padding: 20px;
  box-sizing: border-box;
}


.form-group {
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}

.form-group label {
  font-weight: 600;
  color: #2f5233;
  margin-bottom: 6px;
  font-size: 1.03em;
}

.form-group input,
.form-group select {
  padding: 9px 12px;
  font-size: 1em;
  border: 1.5px solid #b9b9b9;
  border-radius: 8px;
  background: #f3f3f3;
  box-sizing: border-box;
  transition: border-color 0.2s;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #006666;
  background: #eaf6fc;
}

button[type="submit"] {
  background: #2f5233;
  color: white;
  font-weight: 700;
  padding: 12px 0;
  border: none;
  border-radius: 8px;
  font-size: 1.12em;
  cursor: pointer;
  transition: background 0.2s;
}

button[type="submit"]:hover {
  background: #406b38;
}
</style>