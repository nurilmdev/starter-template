
$(document).on('collapsed.lte.pushmenu shown.lte.pushmenu', function(e){
    var displayValue = e.type === 'collapsed' ? 'none' : '';
    $('ul.nav-sidebar li a svg.right').css('display', displayValue);
});
