const el = (id) => document.getElementById(id);

let err, msg;
let sortBroken = false;

function setError(text){ if (err) err.textContent = text; }
function setMsg(text){ if (msg) msg.textContent = text; if (err) err.textContent = ''; }

async function renderStandings() {
  try {
    const res = await fetch('/api/standings');
    const data = await res.json();
    const rows = (sortBroken ? data : data.sort((a,b)=> (b.total||0)-(a.total||0)))
      .map(r => `<tr>
        <td>${escapeHtml(r.name)}</td>
        <td>${r.scores?.["100m"] ?? ''}</td>
        <td>${r.scores?.["Long Jump"] ?? r.scores?.["longJump"] ?? ''}</td>
        <td>${r.scores?.["Shot Put"] ?? r.scores?.["shotPut"] ?? ''}</td>
        <td>${r.scores?.["400m"] ?? ''}</td>
        <td>${r.total ?? 0}</td>
      </tr>`).join('');
    const tbody = el('standings');
    if (tbody) tbody.innerHTML = rows;
  } catch {
    setError('Could not load standings');
  }
}

function escapeHtml(s){
  return String(s).replace(/[&<>"]/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;'}[c]));
}

window.addEventListener('DOMContentLoaded', () => {
  // Hämta element EFTER att DOM finns
  err = el('error');
  msg = el('msg');

  const saveBtn   = el('save');
  const exportBtn = el('export');

  if (!saveBtn) {
    console.warn('Hittar inte knappen med id="save". Kolla index.html.');
  } else {
    saveBtn.addEventListener('click', async () => {
      const name    = (el('name2')?.value || '').trim();
      const eventId = el('event')?.value;
      const rawStr  = el('raw')?.value;
      const raw     = Number(rawStr);

      if (!name) { setError('Name is required'); return; }
      if (!Number.isFinite(raw)) { setError('Enter a valid numeric result'); return; }

      try {
        const res = await fetch('/api/score', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ name, event: eventId, raw })
        });

        if (!res.ok) {
          const t = await res.text().catch(()=> '');
          setError(t || `Score failed (${res.status})`);
          return;
        }

        const json = await res.json().catch(()=> ({}));
        setMsg(`Saved${json.points != null ? `: ${json.points} pts` : ''}`);
        await renderStandings();
      } catch {
        setError('Score failed');
      }
    });
  }

  if (exportBtn) {
    exportBtn.addEventListener('click', async () => {
      try {
        const res = await fetch('/api/export.csv');
        const text = await res.text();
        const blob = new Blob([text], { type: 'text/csv;charset=utf-8' });
        const a = document.createElement('a');
        a.href = URL.createObjectURL(blob);
        a.download = 'results.csv';
        a.click();
        sortBroken = true;
      } catch {
        setError('Export failed');
      }
    });
  }

  renderStandings();
});
