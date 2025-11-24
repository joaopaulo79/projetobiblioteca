(function(){
  const btn = document.querySelector('.menu-toggle');
  const mobile = document.getElementById('mobile-menu');

  btn.addEventListener('click', () => {
    const open = mobile.classList.toggle('open');
    btn.setAttribute('aria-expanded', String(open));
  });

  // Fecha o menu móvel ao clicar em um link
  mobile.addEventListener('click', (e) => {
    if (e.target.tagName === 'A') {
      mobile.classList.remove('open');
      btn.setAttribute('aria-expanded', 'false');
    }
  });
})();
