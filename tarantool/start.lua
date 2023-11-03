box.schema.space.create('message', { if_not_exists = true })

box.schema.sequence.create('mes_id_seq')

box.space.message:create_index('primary', {sequence = 'mes_id_seq', type = "TREE", unique = true, parts = { 1, 'unsigned' } })
box.space.message:create_index('from_id_idx', { type = 'TREE', unique = false, parts = {2, 'unsigned'}, if_not_exists = true })
box.space.message:create_index('to_id_idx', { type = 'TREE', unique = false, parts = {3, 'unsigned'}, if_not_exists = true })

box.space.message:format({{name = 'id', type = 'unsigned'},{name = 'from_id', type = 'unsigned'},{name = 'to_id', type = 'unsigned'},{name = 'text', type = 'string'}})

function create_message(from_id, to_id, message_text)
    local id = box.sequence.mes_id_seq:next()
    box.space.message:insert{id, from_id, to_id, message_text}
    return id
end

function find_by_from_id_and_to_id(from_id, to_id)
    local result = {}
    for _, tuple in box.space.message.index.from_id_idx:pairs({ from_id }, { iterator = "EQ" }) do
        if (tuple[3] == to_id) then
            table.insert(result, tuple)
        end
    end
    return result
end